SELECT i.animal_id as animal_id
from animal_ins i
where name is not null
order by i.animal_id