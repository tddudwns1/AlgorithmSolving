SELECT
    a.APNT_NO,
    p.PT_NAME,
    p.PT_NO,
    a.MCDP_CD,
    d.DR_NAME,
    a.APNT_YMD
from
    PATIENT p
    join
    APPOINTMENT a
        on p.PT_NO = a.PT_NO
    join
    DOCTOR d
        on d.DR_ID = a.MDDR_ID
where
    date_format(APNT_YMD, "%Y-%m-%d") = "2022-04-13"
    and APNT_CNCL_YN = "N"
order by
    APNT_YMD