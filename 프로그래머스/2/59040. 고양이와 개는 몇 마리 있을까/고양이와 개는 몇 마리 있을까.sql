SELECT
    i.animal_type as animal_type,
    count(*) as count
from
    animal_ins i
group by
    i.animal_type
order by
    i.animal_type