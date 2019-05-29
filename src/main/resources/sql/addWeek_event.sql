create procedure addWeek()
  begin
    update LabManagerTest.t_systemparameter
    set this_week = this_week + 1;
  end


    alter event addWeek_event
    on schedule every 7 day
    starts '2017-03-03 09:05:01' -- 开始时间（mysql时区问题，部署时注意检查）
do call addWeek();

SELECT *
FROM mysql.event;

select curtime();

show variables like "%time_zone%";

set global time_zone = '+8:00';
set time_zone = '+8:00';
flush privileges;