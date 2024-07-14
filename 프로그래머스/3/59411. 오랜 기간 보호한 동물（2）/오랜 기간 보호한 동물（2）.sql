select i.animal_id as animal_id, i.name as name
from animal_outs o
join animal_ins i using (animal_id)
order by datediff(o.datetime, i.datetime) desc
limit 2;