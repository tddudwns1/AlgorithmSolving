select
    o.CAR_ID,
    case
        when b.CAR_ID is null then "대여 가능"
        else "대여중"
    end as AVAILABILITY
from
    (
        select 
            distinct(CAR_ID)
        from
            CAR_RENTAL_COMPANY_RENTAL_HISTORY 
    ) o
    left join 
    (
        select
            CAR_ID
        from
            CAR_RENTAL_COMPANY_RENTAL_HISTORY
        where
            "2022-10-16" between START_DATE and END_DATE
    ) b
        on o.CAR_ID = b.CAR_ID
order by
    CAR_ID desc