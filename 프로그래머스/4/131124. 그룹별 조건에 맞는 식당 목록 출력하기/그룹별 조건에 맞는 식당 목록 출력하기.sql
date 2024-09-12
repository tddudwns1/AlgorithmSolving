SELECT
    mp.MEMBER_NAME,
    rr.REVIEW_TEXT,
    date_format(rr.REVIEW_DATE, "%Y-%m-%d") as REVIEW_DATE
from
    REST_REVIEW rr
    join
    (
        select
            MEMBER_ID,
            REVIEW_TEXT,
            REVIEW_DATE
        from
            REST_REVIEW
        group by
            MEMBER_ID
        having
            count(MEMBER_ID)
        order by
            count(MEMBER_ID) desc
        limit 1
    ) rrs
        on rrs.MEMBER_ID = rr.MEMBER_ID
    join
        MEMBER_PROFILE mp
        on rr.MEMBER_ID = mp.MEMBER_ID
order by
    REVIEW_DATE, REVIEW_TEXT

# select
#             MEMBER_ID,
#             REVIEW_TEXT,
#             REVIEW_DATE
#         from
#             REST_REVIEW
#             order by MEMBER_ID