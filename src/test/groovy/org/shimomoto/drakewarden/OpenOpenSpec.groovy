package org.shimomoto.drakewarden

import org.shimomoto.drakewarden.api.Range

import java.time.LocalDate
import java.time.chrono.ChronoLocalDate

import spock.lang.Specification

class OpenOpenSpec extends Specification {
	def "Simple range test"() {
		given: 'range [0,1)'
		final Range<Integer> r = Ranges.openOpen(0i,2i)

		expect: 'contains 1'
		r.contains(1i)
		r.test(1i)
		and: 'does not contain any other integer'
		!r.contains(0i)
		!r.test(0i)
		!r.contains(2i)
		!r.test(2i)
		!r.contains(Integer.MIN_VALUE)
		!r.contains(-1i)
		!r.contains(10i)
		!r.contains(Integer.MAX_VALUE)
	}

	def "LocalDate range test"() {
		given: 'range [2021-06-01,2021-06-23)'
		final LocalDate left = LocalDate.of(2021, 6, 1)
		final LocalDate right = LocalDate.of(2021, 6, 23)
		final Range<ChronoLocalDate> r = Ranges.openOpen(left, right)

		expect: 'contains '
		r.contains(left + 1)
		r.test(left + 1)
		r.contains(left + 12)
		r.contains(right -1)
		r.test(right - 1)
		and: 'does not contain any other integer'
		!r.contains(left)
		!r.contains(right)
		!r.contains(LocalDate.of(2020, 1 ,1))
		!r.contains(LocalDate.of(2022, 1 ,1))
	}

	def "isDegenerate accepts"() {
		given:
		final def rg = Ranges.openOpen(l, l)

		expect:
		rg.isDegenerate()

		where:
		_ | l
		0 | 0i
		1 | LocalDate.of(2021, 6, 1)
		2 | 123456L
		3 | 9.9 //BigDecimal
	}

	def "isDegenerate refuses"() {
		given:
		final def rg = Ranges.openOpen(l, r)

		expect:
		!rg.isDegenerate()

		where:
		_ | l                        | r
		0 | 0i                       | 1i
		1 | LocalDate.of(2021, 6, 1) | LocalDate.of(2021, 6, 23)
		2 | 123456L                  | 654321L
		3 | 9.9                      | 10.1 //BigDecimal
	}

	def "left checks"() {
		given: 'range [0,1)'
		final Range<Integer> r = Ranges.openOpen(0i, 1i)

		expect:
		r.left == 0i
		!r.leftClosed
		r.leftOpen
	}

	def "right checks"() {
		given: 'range [0,1)'
		final Range<Integer> r = Ranges.openOpen(0i, 1i)

		expect:
		r.right == 1i
		!r.rightClosed
		r.rightOpen
	}
}
