package org.javaero.demo.springboot.entity;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface ParticipantDao extends CrudRepository<Participant, Long> {
}
