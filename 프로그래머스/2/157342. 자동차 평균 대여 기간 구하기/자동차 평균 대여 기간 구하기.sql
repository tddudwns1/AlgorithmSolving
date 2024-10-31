select
    CAR_ID,
    round(DURATION, 1) as AVERAGE_DURATION
from
    (
        select
            CAR_ID,
            avg(datediff(END_DATE, START_DATE)) + 1 as DURATION
        from
            CAR_RENTAL_COMPANY_RENTAL_HISTORY
        group by
            CAR_ID
    ) d
where
    d.DURATION >= 7
order by
    AVERAGE_DURATION desc, CAR_ID desc