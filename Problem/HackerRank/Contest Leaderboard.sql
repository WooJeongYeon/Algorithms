select max(h.hacker_id), max(h.name), sum(s.score)
from hackers h
         join (
    select max(hacker_id) hacker_id, max(challenge_id) challenge_id, max(score) score
    from submissions
    group by challenge_id, hacker_id
) s
              on h.hacker_id = s.hacker_id
group by h.hacker_id
having sum(s.score) > 0
order by sum(s.score) desc, h.hacker_id asc;