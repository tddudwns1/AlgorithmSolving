SELECT
    a.MCDP_CD as 진료과코드,
    count(*) as 5월예약건수
from
    appointment a
where
    year(a.APNT_YMD) = 2022
    and month(a.APNT_YMD) = 05
group by
    a.MCDP_CD
order by
    5월예약건수,
    진료과코드

# SELECT
#     *
# from
    # appointment a