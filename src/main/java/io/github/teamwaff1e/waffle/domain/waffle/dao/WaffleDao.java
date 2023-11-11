package io.github.teamwaff1e.waffle.domain.waffle.dao;

import io.github.teamwaff1e.waffle.domain.member.entity.Follow;
import io.github.teamwaff1e.waffle.global.dao.CrudDao;
import io.github.teamwaff1e.waffle.domain.waffle.entity.Waffle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Component
@RequiredArgsConstructor
public class WaffleDao implements CrudDao<Waffle, Long> {

    private final EntityManager entityManager;

    @Override
    public Waffle save(Waffle waffle) {
        entityManager.persist(waffle);
        return waffle;
    }

    @Override
    public Optional<Waffle> findById(Long waffleId) {
        Waffle waffle = entityManager.find(Waffle.class, waffleId);
        return Optional.ofNullable(waffle);
    }

    @Override
    public void delete(Waffle waffle) {
        entityManager.remove(waffle);
    }

    public List<Waffle> findAll() {
        return null;
    }

    public Page<Waffle> findByIdxLessThanAndFollowInOrderByIdDesc(Long lastWaffleIdx, List<Follow> follows, PageRequest pageRequest) {
        List<Long> followIds = follows.stream()
                .map(Follow::getFollowingId)
                .collect(Collectors.toList());

        String jpql = "SELECT w FROM Waffle w " +
                "WHERE w.id > :lastWaffleIdx " +
                "AND w.member.id IN :followIds " +
                "ORDER BY w.createdAt DESC ";

        TypedQuery<Waffle> query = entityManager.createQuery(jpql, Waffle.class);
        query.setParameter("lastWaffleIdx", lastWaffleIdx);
        query.setParameter("followIds", followIds);

        query.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
        query.setMaxResults(pageRequest.getPageSize());

        List<Waffle> result = query.getResultList();

        return new PageImpl<>(result, pageRequest, result.size());
    }

    public Page<Waffle> findByIdxLessThanAndMemberInOrderByIdDesc(Long lastWaffleIdx, Long memberId, PageRequest pageRequest) {
            String jpql = "SELECT w FROM Waffle w " +
                    "WHERE w.id > :lastWaffleIdx " +
                    "AND w.member.id = :memberId " +
                    "ORDER BY w.createdAt DESC ";

        TypedQuery<Waffle> query = entityManager.createQuery(jpql, Waffle.class);
        query.setParameter("lastWaffleIdx", lastWaffleIdx);
        query.setParameter("memberId", memberId);

        query.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
        query.setMaxResults(pageRequest.getPageSize());

        List<Waffle> result = query.getResultList();

        return new PageImpl<>(result, pageRequest, result.size());
    }

    public Optional<Waffle> findWaffleByWaffleIdAndMemberId(Long waffleId, Long memberId) {
        String jpql = "SELECT w FROM Waffle w " +
                "WHERE w.id = :waffleId " +
                "AND w.member.id = :memberId";

        TypedQuery<Waffle> query = entityManager.createQuery(jpql, Waffle.class);
        query.setParameter("waffleId", waffleId);
        query.setParameter("memberId", memberId);

        return Optional.ofNullable(query.getSingleResult());
    }
}
