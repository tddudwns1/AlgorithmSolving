SELECT
    floor(sum(DAILY_FEE) / count(*)) as AVERAGE_FEE
from
    CAR_RENTAL_COMPANY_CAR 
where
    CAR_TYPE = "SUV"
group by
    CAR_TYPE