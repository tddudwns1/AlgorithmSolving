select
    o.ID,
    o.NAME,
    o.HOST_ID
from
    PLACES o
    join
    (
        select
            *
        from
            PLACES
        group by
            HOST_ID
        having
            count(*) >= 2
    ) p
        on o.HOST_ID = p.HOST_ID
order by
    ID
