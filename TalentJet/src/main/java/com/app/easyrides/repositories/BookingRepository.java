package com.app.easyrides.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import com.app.easyrides.entities.Booking;
import com.app.easyrides.entities.QBooking;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>, QuerydslPredicateExecutor<Booking>,
		QuerydslBinderCustomizer<QBooking> {

	@Override
	public default void customize(QuerydslBindings bindings, QBooking root) {
		bindings.bind(String.class).first((SingleValueBinding<StringPath, String>)StringExpression::containsIgnoreCase);
		bindings.excluding(root.bookingId);
	}

}
