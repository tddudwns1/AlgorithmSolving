select
    MONTH(o.START_DATE) as MONTH,
    o.CAR_ID,
    count(*) as RECORDS
from
    CAR_RENTAL_COMPANY_RENTAL_HISTORY o
    join
    (
        select
            *
        from
            CAR_RENTAL_COMPANY_RENTAL_HISTORY
        where
            date_format(START_DATE, "%Y-%m") between "2022-08" and "2022-10"
        group by
            CAR_ID
        having
            count(*) >= 5
    ) p
        on o.CAR_ID = p.CAR_ID
where
    date_format(o.START_DATE, "%Y-%m") between "2022-08" and "2022-10"
group by
    MONTH, CAR_ID
order by
    MONTH, CAR_ID desc