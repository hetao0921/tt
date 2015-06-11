/usr/local/mysql/support-files/mysql.server stop
/usr/local/mysql/support-files/mysql.server start
echo 'exit' | /usr/local/mysql/bin/mysql -hlocalhost -P3306 -uadmin -p111