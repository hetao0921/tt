<!--
  Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<!DOCTYPE html>
<html>
<head>
    <title>WebSocket/SockJS Echo Sample (Adapted from Tomcat's echo sample)</title>
    <style type="text/css">
        #connect-container {
            float: left;
            width: 400px
        }

        #connect-container div {
            padding: 5px;
        }

        #console-container {
            float: left;
            margin-left: 15px;
            width: 400px;
        }

        #console {
            border: 1px solid #CCCCCC;
            border-right-color: #999999;
            border-bottom-color: #999999;
            height: 170px;
            overflow-y: scroll;
            padding: 5px;
            width: 100%;
        }
        #console p {
            padding: 0;
            margin: 0;
        }
        #userinfo {
            border: 1px solid #CCCCCC;
            border-right-color: #999999;
            border-bottom-color: #999999;
            height: 170px;
            overflow-y: scroll;
            padding: 5px;
            width: 100%;
        }
        #userinfo p {
            padding: 0;
            margin: 0;
        }

    </style>

    <script src="js/json.js"></script>

    <script type="text/javascript">
        var ws = null;
        var url = null;
        var transports = Array();

        function setConnected(connected) {
            document.getElementById('echo').disabled = !connected;
            document.getElementById('enter').disabled = !connected;
        }

        function connect(urlPath) {
          if (urlPath.indexOf('sockjs') != -1) {
                url = urlPath;
                document.getElementById('sockJsTransportSelect').style.visibility = 'visible';
            }
            else {
              if (window.location.protocol == 'http:') {
                  url = 'ws://localhost:8887'  + urlPath;
              } else {
                  url = 'wss://localhost:8887'  + urlPath;
              }
            }
            if (!url) {
                alert('Select whether to use W3C WebSocket or SockJS');
                return;
            }

            ws = (url.indexOf('sockjs') != -1) ? 
                new SockJS(url, undefined, {protocols_whitelist: transports}) : new WebSocket(url);

            ws.onopen = function () {
                setConnected(true);
                log('Info: connection opened.');
            };
            ws.onmessage = function (event) {
                log('Received: ' + event.data);
            };
            ws.onclose = function (event) {
                setConnected(false);
                log('Info: connection closed.');
                log(event);
            };
        }

        function disconnect() {
            if (ws != null) {
                ws.close();
                ws = null;
            }
            setConnected(false);
        }

        function echo() {
            if (ws != null) {
                var message = document.getElementById('message').value;
                log('Sent: ' + message);
                 var b='{"messageType":"commonMessage","messageContent":"'+message+'"}';
                ws.send(b);
            } else {
                alert('connection not established, please connect.');
            }
        }

        function updateUrl(urlPath) {
            if (urlPath.indexOf('sockjs') != -1) {
                url = urlPath;
                document.getElementById('sockJsTransportSelect').style.visibility = 'visible';
            }
            else {
              if (window.location.protocol == 'http:') {
                  url = 'ws://localhost:8887'  + urlPath;
              } else {
                  url = 'wss://localhost:8887'  + urlPath;
              }
              document.getElementById('sockJsTransportSelect').style.visibility = 'hidden';
            }
        }

        function updateTransport(transport) {
          transports = (transport == 'all') ?  [] : [transport];
        }
        
        function log(message) {
            var console = document.getElementById('console');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            var msg=message.split(":");
            p.appendChild(document.createTextNode(msg));
            console.appendChild(p);
            while (console.childNodes.length > 25) {
                console.removeChild(console.firstChild);
            }
            console.scrollTop = console.scrollHeight;
            
           if(msg[3]!=undefined){
            listUser(msg[3]);
           }
        }
        
        function listUser(message){
        document.getElementById('userinfo').innerHTML="";
        var userinfo=document.getElementById('userinfo');
         var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(document.createTextNode(message));
            userinfo.appendChild(p);
            while (userinfo.childNodes.length > 25) {
                userinfo.removeChild(userinfo.firstChild);
            }
            userinfo.scrollTop = userinfo.scrollHeight;
        }
        
        function enter(){
        var platform=document.getElementById('enter').innerHTML;
        var b='{"messageType":"changeRoom","platForm":"'+platform+'"}';
        ws.send(b);
        
        }
        
        function onload(){
        connect("/WebSocket/websocket");
        }
        
        function closepage(){
        disconnect();
        }
    </script>
</head>
<body onload="onload()" onunload="closepage()">
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websockets 
    rely on Javascript being enabled. Please enable
    Javascript and reload this page!</h2></noscript>
<div>
    <div id="connect-container">

        <div>
            <textarea id="message" style="width: 350px">Here is a message!</textarea>
        </div>
        <div>
            <button id="echo" onclick="echo();" disabled="disabled">Echo message</button>
        </div>
           <div>
            <button id="enter" onclick="enter();" disabled="disabled">Enter A Room</button>
        </div>
         <div id="userinfo"></div>
    </div>
    <div id="console-container">
        <div id="console"></div>
    </div>
</div>
</body>
</html>
