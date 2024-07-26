SELECT 
    i.animal_type as animal_type,
    case
        when name is null then "No name"
        else name
    end as name,
    i.sex_upon_intake
from
    animal_ins i
order by
    i.animal_id