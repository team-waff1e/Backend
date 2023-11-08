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

    public Page<Waffle> findByIdxLessThanAndMemberInOrderByIdDesc(Long lastWaffleIdx, List<Follow> follows, PageRequest pageRequest) {
        String jpql = "SELECT w FROM Waffle w " +
                "WHERE w.id < :lastWaffleIdx " +
                "AND w.memberId IN :follows " +
                "ORDER BY w.id DESC ";

        TypedQuery<Waffle> query = entityManager.createQuery(jpql, Waffle.class);
        query.setParameter("lastWaffleIdx", lastWaffleIdx);
        query.setParameter("follows", follows);

        query.setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize());
        query.setMaxResults(pageRequest.getPageSize());

        List<Waffle> result = query.getResultList();

        String countJpql = "SELECT COUNT(w) FROM Waffle w " +
                "WHERE w.id < :lastWaffleIdx " +
                "AND w.memberId IN :follows";

        TypedQuery<Long> countQuery = entityManager.createQuery(countJpql, Long.class);
        countQuery.setParameter("lastWaffleIdx", lastWaffleIdx);
        countQuery.setParameter("follows", follows);

        Long totalCount = countQuery.getSingleResult();

        return new PageImpl<>(result, pageRequest, totalCount);
    }

    public List<Waffle> findWaffleListByMemberId(Long waffleId) {
        String jpql = "SELECT w FROM Waffle w " +
                "WHERE w.memberId = :waffleId";

        TypedQuery<Waffle> query = entityManager.createQuery(jpql, Waffle.class);
        query.setParameter("waffleId", waffleId);

        return query.getResultList();
    }

    public Optional<Waffle> findWaffleByWaffleIdAndMemberId(Long waffleId, Long memberId) {
        String jpql = "SELECT w FROM Waffle w " +
                "WHERE w.id = :waffleId " +
                "AND w.memberId = :memberId";

        TypedQuery<Waffle> query = entityManager.createQuery(jpql, Waffle.class);
        query.setParameter("waffleId", waffleId);
        query.setParameter("memberId", memberId);


        return Optional.ofNullable(query.getSingleResult());
    }
}
