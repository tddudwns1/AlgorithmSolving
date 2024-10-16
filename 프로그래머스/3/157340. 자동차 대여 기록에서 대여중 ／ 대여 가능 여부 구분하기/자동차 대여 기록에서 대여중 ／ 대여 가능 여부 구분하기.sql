select
    CAR_ID,
    case
        when 
            exists (
                select
                    1
                from
                    CAR_RENTAL_COMPANY_RENTAL_HISTORY h
                where
                    h.CAR_ID = c.CAR_ID
                    and '2022-10-16' between h.START_DATE and h.END_DATE
            )
        then "대여중"
        else "대여 가능"
    end
    as AVAILABILITY
from
    (
        select distinct
            CAR_ID
        from
            CAR_RENTAL_COMPANY_RENTAL_HISTORY
    ) c
order by
    CAR_ID desc