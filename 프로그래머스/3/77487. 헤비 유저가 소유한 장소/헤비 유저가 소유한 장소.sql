SELECT
    ID,
    NAME,
    o.HOST_ID
from
    PLACES o
    join (
        select
            HOST_ID
        from
            PLACES
        group by
            HOST_ID
        having
            count(*) > 1
    ) s
        on o.HOST_ID = s.HOST_ID
order by
    ID