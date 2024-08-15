SELECT distinct
    c.CAR_ID,
    c.CAR_TYPE,
    round(c.DAILY_FEE * 30 * (100 - d.DISCOUNT_RATE) / 100, 0)
    as FEE
from
    CAR_RENTAL_COMPANY_CAR c
    join
    CAR_RENTAL_COMPANY_DISCOUNT_PLAN d using(CAR_TYPE)
where
    c.CAR_TYPE in ('세단', 'SUV') and
    d.DURATION_TYPE = '30일 이상' and
    c.DAILY_FEE * 30 * (100 - d.DISCOUNT_RATE) / 100 between 500000 and 2000000 and
    not exists (
        select
            *
        from
            CAR_RENTAL_COMPANY_RENTAL_HISTORY r
        where
            c.CAR_ID = r.CAR_ID and
            r.START_DATE <= '2022-11-30' and
            r.END_DATE >= '2022-11-01'
    )
order by
    FEE desc,
    CAR_TYPE,
    CAR_ID desc