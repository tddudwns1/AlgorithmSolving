SELECT animal_id as animal_id, name as name
from animal_ins
where animal_type = "Dog"
    and name like "%el%"
order by name