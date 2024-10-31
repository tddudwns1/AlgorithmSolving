select 
    distinct(ID),
    EMAIL,
    FIRST_NAME,
    LAST_NAME
from
    DEVELOPERS d
    join
    SKILLCODES s
        on s.NAME in ("Python", "C#")
        and s.CODE & d.SKILL_CODE = s.CODE
order by
    ID
















# SELECT DISTINCT
#     ID,
#     EMAIL,
#     FIRST_NAME,
#     LAST_NAME
# FROM
#     DEVELOPERS
#     JOIN
#     SKILLCODES
#     ON DEVELOPERS.SKILL_CODE & SKILLCODES.CODE
# WHERE
#     NAME="C#"
#    OR NAME="Python"
# ORDER BY
#     ID