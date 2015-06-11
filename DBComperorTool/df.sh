/usr/local/mysql/support-files/mysql.server stop
/usr/local/mysql/support-files/mysql.server start
echo 'exit' | /usr/local/mysql/bin/mysql -hdf -P3306 -uff -pff