select
    CAR_ID,
    round(avg(END_DATE - START_DATE + 1), 1) as AVERAGE_DURATION
from
    CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by
    CAR_ID
having
    avg(date_diff(END_DATE, START_DATE) + 1) >= 7
order by
    AVERAGE_DURATION desc,
    CAR_ID desc