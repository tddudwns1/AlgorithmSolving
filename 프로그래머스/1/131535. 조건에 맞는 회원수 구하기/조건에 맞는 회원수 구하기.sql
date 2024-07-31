SELECT
    count(*) as users
from
    user_info u
where
    age >= 20 and age <= 29 and
    date_format(joined, "%Y-%m-%d") >= "2021-01-01" and 
    date_format(joined, "%Y-%m-%d") <= "2021-12-31"