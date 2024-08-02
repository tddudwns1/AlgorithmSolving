SELECT
    i.ANIMAL_ID as ANIMAL_ID,
    i.NAME as NAME,
    case
        when i.SEX_UPON_INTAKE like "Neutered%"
        then "O"
        when i.SEX_UPON_INTAKE like "Spayed%"
        then "O"
        else "X"
    end as 중성화
from
    ANIMAL_INS i
