select
    year(o.SALES_DATE) as YEAR,
    month(o.SALES_DATE) as MONTH,
    GENDER,
    count(distinct(u.USER_ID)) as USERS
from
    USER_INFO u
    join
    ONLINE_SALE o
        on u.USER_ID = o.USER_ID
        and u.GENDER is not null
group by
    YEAR,
    MONTH,
    GENDER
order by
    YEAR,
    MONTH,
    GENDER