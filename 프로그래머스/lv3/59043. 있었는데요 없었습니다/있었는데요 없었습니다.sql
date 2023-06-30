SELECT i.ANIMAL_ID, i.NAME
from animal_ins i
join animal_outs o
on i.animal_id = o.animal_id
where i.datetime > o.datetime
order by i.datetime;