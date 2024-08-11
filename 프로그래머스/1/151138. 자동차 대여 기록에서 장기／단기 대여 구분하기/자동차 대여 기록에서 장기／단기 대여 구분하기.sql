SELECT
    c.HISTORY_ID as HISTORY_ID,
    c.CAR_ID as CAR_ID,
    date_format(START_DATE, "%Y-%m-%d") as START_DATE,
    date_format(END_DATE, "%Y-%m-%d") as END_DATE,
    case
        when datediff(c.END_DATE, c.START_DATE) < 29 then "단기 대여"
        else "장기 대여"
    end
    as RENT_TYPE
from
    CAR_RENTAL_COMPANY_RENTAL_HISTORY c
where
    c.START_DATE BETWEEN "2022-09-01" and "2022-09-30"
order by
    HISTORY_ID desc