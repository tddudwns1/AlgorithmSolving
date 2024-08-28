SELECT
    YEAR(s.SALES_DATE) as YEAR,
    MONTH(s.SALES_DATE) as MONTH,
    count(distinct(u.USER_ID)) as PURCHASED_USERS,
    round(
        count(distinct(u.USER_ID))
        /
        (
            select
                count(*) as total
            from
                USER_INFO 
            where
                year(JOINED) = '2021'
        )
        , 1) as PUCHASED_RATIO
from
    USER_INFO u
    join
    ONLINE_SALE s
        on u.USER_ID = s.USER_ID
where
    YEAR(u.JOINED) = '2021'
group by
    YEAR, MONTH
order by
    YEAR,
    MONTH
