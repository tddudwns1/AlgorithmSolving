SELECT
    ANIMAL_ID,
    o.NAME
from
    ANIMAL_INS i
    right join ANIMAL_OUTS o using(ANIMAL_ID)
where
    i.INTAKE_CONDITION is null
order by
    ANIMAL_ID