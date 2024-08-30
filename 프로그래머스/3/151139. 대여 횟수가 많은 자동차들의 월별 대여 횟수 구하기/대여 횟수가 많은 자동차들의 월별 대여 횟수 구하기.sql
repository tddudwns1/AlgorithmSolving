# SELECT
#     MONTH(START_DATE) as MONTH,
#     CAR_ID,
#     count(*) as RECORDS
# from
#     CAR_RENTAL_COMPANY_RENTAL_HISTORY
# where
#     YEAR(START_DATE) = 2022
#     and MONTH(START_DATE) between 8 and 10
# group by
#     CAR_ID, MONTH
# having
#     count(CAR_ID) >= 5
# order by
#     MONTH,
#     CAR_ID desc
    
# # 대여 시작일을 기준으로 2022년 8월부터 2022년 10월까지 총 대여 횟수가 5회 이상인 자동차
# # 해당 기간 동안의 월별 자동차 ID 별 총 대여 횟수(컬럼명: RECORDS)
# # 월의 총 대여 횟수가 0인 경우에는 결과에서 제외

SELECT
    MONTH(START_DATE) as MONTH,
    o.CAR_ID,
    count(*) as RECORDS
from
    CAR_RENTAL_COMPANY_RENTAL_HISTORY o
    join
    (
        SELECT
            CAR_ID
        from
            CAR_RENTAL_COMPANY_RENTAL_HISTORY
        where
            YEAR(START_DATE) = 2022
            and MONTH(START_DATE) between 8 and 10
        group by
            CAR_ID
        having
            count(CAR_ID) >= 5
    ) s
        on o.CAR_ID = s.CAR_ID
where
    YEAR(START_DATE) = 2022
    and MONTH(START_DATE) between 8 and 10
group by
    o.CAR_ID, MONTH(START_DATE)
order by
    MONTH,
    CAR_ID desc