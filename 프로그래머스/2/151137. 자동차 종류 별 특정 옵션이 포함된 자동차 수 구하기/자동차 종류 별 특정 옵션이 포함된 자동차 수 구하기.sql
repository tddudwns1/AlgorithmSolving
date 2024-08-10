SELECT
    c.car_type as car_type,
    count(*) as cars
from
    CAR_RENTAL_COMPANY_CAR c
where
    c.options like "%통풍시트%" or
    c.options like "%열선시트%" or
    c.options like "%가죽시트%" 
group by
    c.car_type
order by
    c.car_type