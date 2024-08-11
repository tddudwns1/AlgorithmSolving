SELECT
    i.ANIMAL_ID as ANIMAL_ID,
    i.ANIMAL_TYPE as ANIMAL_TYPE,
    i.NAME as NAME
from
    (
        select
            i.ANIMAL_ID as ANIMAL_ID,
            i.ANIMAL_TYPE as ANIMAL_TYPE,
            i.NAME as NAME
        from
            ANIMAL_INS i
        where
            SEX_UPON_INTAKE like "Intact%"
    ) i
    join
    (
        select
            o.ANIMAL_ID as ANIMAL_ID
        from
            ANIMAL_OUTS o
        where
            SEX_UPON_OUTCOME not like "Intact%"
    ) o
    using(animal_id)
order by
    i.ANIMAL_ID