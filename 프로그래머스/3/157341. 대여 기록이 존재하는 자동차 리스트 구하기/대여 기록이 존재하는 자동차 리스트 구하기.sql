SELECT
    distinct CAR_ID
from
    CAR_RENTAL_COMPANY_CAR c join CAR_RENTAL_COMPANY_RENTAL_HISTORY r using(CAR_ID)
where
    c.CAR_TYPE = "세단"
    and month(r.START_DATE) = 10
order by
    CAR_ID desc