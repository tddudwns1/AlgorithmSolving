SELECT
    i.animal_id as animal_id,
    i.name as name,
    date_format(i.datetime, "%Y-%m-%d") as "날짜"
from
    animal_ins i
order by i.animal_id