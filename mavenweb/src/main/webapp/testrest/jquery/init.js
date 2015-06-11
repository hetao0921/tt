/**
 * Created by lizehua on 2015/2/27.
 */
var selectText="";


$(function() {

    $.get("no-cross-domain-url.xml",function(data){

        $(data).find("url").each(function(){
            var type=$(this).attr("type");
            var name=$(this).attr("name");
            var url=$(this).text()+"||"+type;

            var option='<option name="url" value="'+url+'">'+name+'</option>';
            selectText=selectText+option;

        });
        $("#selectUrl").html(selectText);
        selectText="";
    });



});
