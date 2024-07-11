SELECT a.DATETIME as DATETIME
from (
    SELECT a.DATETIME as DATETIME
    from ANIMAL_INS a
    order by DATETIME
) a
where rownum <= 1;
