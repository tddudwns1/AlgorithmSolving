SELECT
    dr_name as dr_name,
    dr_id as dr_id,
    mcdp_cd as mcdp_cd,
    date_format(hire_ymd, "%Y-%m-%d") as hire_ymd
from
    doctor
where
    mcdp_cd in ("cs", "gs")
order by
    hire_ymd desc,
    dr_name;