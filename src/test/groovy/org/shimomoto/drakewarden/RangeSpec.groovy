package org.shimomoto.drakewarden

import java.time.LocalDate
import java.time.chrono.ChronoLocalDate

import spock.lang.Specification

class RangeSpec extends Specification {
	def "Simple range test"() {
		given: 'range [0,1)'
		Range<Integer> r = Ranges.closedOpen(0i,1i)

		expect: 'contains 0'
		r.contains(0i)
		r.test(0i)
		and: 'does not contain any other integer'
		!r.contains(Integer.MIN_VALUE)
		!r.contains(-1i)
		!r.contains(1i)
		!r.test(1i)
		!r.contains(10i)
		!r.contains(Integer.MAX_VALUE)
	}

	def "LocalDate range test"() {
		given: 'range [2021-06-01,2021-06-23)'
		LocalDate left = LocalDate.of(2021, 6, 1)
		LocalDate right = LocalDate.of(2021, 6, 23)
		Range<ChronoLocalDate> r = Ranges.closedOpen(left, right)

		expect: 'contains 0'
		r.contains(left)
		r.test(left)
		r.contains(left + 1)
		r.contains(left + 12)
		r.contains(left + 21)
		and: 'does not contain any other integer'
		!r.contains(left - 1)
		!r.contains(left + 22)
		!r.test(left + 22)
		!r.contains(LocalDate.of(2020, 1 ,1))
		!r.contains(LocalDate.of(2022, 1 ,1))
	}
}
