/usr/local/mysql/support-files/mysql.server stop
/usr/local/mysql/support-files/mysql.server start
echo 'exit' | /usr/local/mysql/bin/mysql -hf -P3306 -udd -pd