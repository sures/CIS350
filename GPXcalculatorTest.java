/**
 * Test for the GPXcalculator class.
 */
package edu.upenn.cis350.gpx;


import java.util.ArrayList;
import java.util.Date;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author Rupi Sureshkumar
 *
 */
public class GPXcalculatorTest {
	// fake date for input to all points created because date functionality does
	// not need to be tested
	Date fakeDate = new Date();
	
	@Test
	// tests that distance calculated is -1 when the GPXtrk is null
	public void testNullTrack() {
		GPXtrk nullTrack = null;
		assertEquals(-1.0, GPXcalculator.calculateDistanceTraveled(nullTrack), 0.0);
	}
	
	@Test
	// tests that distance calculated is -1 when GPXtrk is empty
	public void testEmptyTrack() {
		ArrayList<GPXtrkseg> noSegments = new ArrayList<GPXtrkseg>();
		GPXtrk emptyTrack = new GPXtrk("empty", noSegments);
		assertEquals(-1.0, GPXcalculator.calculateDistanceTraveled(emptyTrack), 0.0);
	}
	
	@Test
	// tests that distance calculated for a segment is 0 when that GPXtrkseg is null
	public void testNullSegment() {		
		GPXtrkseg nullSegment = null;
		ArrayList<GPXtrkseg> segments = new ArrayList<GPXtrkseg>();
		segments.add(nullSegment);
		GPXtrk oneSegmentNull = new GPXtrk("null segment", segments);
		assertEquals(0.0, GPXcalculator.calculateDistanceTraveled(oneSegmentNull), 0.0);		
	}
	
	@Test
	// tests that distance calculated for a segment is 0 when there are no points in
	// the segment
	public void testEmptySegment() {		
		GPXtrkseg emptySegment = new GPXtrkseg(new ArrayList<GPXtrkpt>());
		ArrayList<GPXtrkseg> segments = new ArrayList<GPXtrkseg>();
		segments.add(emptySegment);
		GPXtrk oneEmptySegment = new GPXtrk("one empty", segments);
		assertEquals(0.0, GPXcalculator.calculateDistanceTraveled(oneEmptySegment), 0.0);		
	}
	
	@Test
	// tests that distance calculated for a segment is 0 when there is only one point in 
	// the segment
	public void testSegmentWithOnePoint() {
		GPXtrkpt A = new GPXtrkpt(70.0, 110.0, fakeDate);
		ArrayList<GPXtrkpt> points = new ArrayList<GPXtrkpt>();
		points.add(A);
		GPXtrkseg onePointSegment = new GPXtrkseg(points);
		ArrayList<GPXtrkseg> segments = new ArrayList<GPXtrkseg>();
		segments.add(onePointSegment);
		GPXtrk oneSegmentWithOnePoint = new GPXtrk("one point", segments);
		assertEquals(0.0, GPXcalculator.calculateDistanceTraveled(oneSegmentWithOnePoint), 0.0);		
	}
	
	@Test
	// tests that distance calculated for a segment is 0 when a track point is null
	public void testNullTrackPoint() {
		GPXtrkpt nullPoint = null;
		ArrayList<GPXtrkpt> points = new ArrayList<GPXtrkpt>();
		points.add(nullPoint);
		GPXtrkseg nullPointSegment = new GPXtrkseg(points);
		ArrayList<GPXtrkseg> segments = new ArrayList<GPXtrkseg>();
		segments.add(nullPointSegment);
		GPXtrk segmentWithNullPoint = new GPXtrk("null point", segments);
		assertEquals(0.0, GPXcalculator.calculateDistanceTraveled(segmentWithNullPoint), 0.0);		
	}

	@Test
	// tests that distance calculated for a segment is 0 when a track has a latitude
	// greater than 90
	public void testTooLargeLatitude() {
		GPXtrkpt A = new GPXtrkpt(70.0, 110.0, fakeDate);
		GPXtrkpt bigLatitude = new GPXtrkpt(91.0, 100.0, fakeDate);
		ArrayList<GPXtrkpt> points = new ArrayList<GPXtrkpt>();
		points.add(A);
		points.add(bigLatitude);
		GPXtrkseg bigLatitudeSegment = new GPXtrkseg(points);
		ArrayList<GPXtrkseg> segments = new ArrayList<GPXtrkseg>();
		segments.add(bigLatitudeSegment);
		GPXtrk segmentWithBigLatitude = new GPXtrk("big latitude", segments);
		assertEquals(0.0, GPXcalculator.calculateDistanceTraveled(segmentWithBigLatitude), 0.0);		
	}
	
	@Test
	// tests that distance calculated for a segment is 0 when a track has a latitude
	// less than -90
	public void testTooSmallLatitude() {
		GPXtrkpt A = new GPXtrkpt(70.0, 110.0, fakeDate);
		GPXtrkpt smallLatitude = new GPXtrkpt(-91.0, 100.0, fakeDate);
		ArrayList<GPXtrkpt> points = new ArrayList<GPXtrkpt>();
		points.add(A);
		points.add(smallLatitude);
		GPXtrkseg smallLatitudeSegment = new GPXtrkseg(points);
		ArrayList<GPXtrkseg> segments = new ArrayList<GPXtrkseg>();
		segments.add(smallLatitudeSegment);
		GPXtrk segmentWithSmallLatitude = new GPXtrk("big latitude", segments);
		assertEquals(0.0, GPXcalculator.calculateDistanceTraveled(segmentWithSmallLatitude), 0.0);
	}

	@Test
	// tests that distance calculated for a segment is 0 when a track has a longitude
	// greater than 180
	public void testTooLargeLongitude() {
		GPXtrkpt A = new GPXtrkpt(70.0, 110.0, fakeDate);
		GPXtrkpt bigLongitude = new GPXtrkpt(80.0, 181.0, fakeDate);
		ArrayList<GPXtrkpt> points = new ArrayList<GPXtrkpt>();
		points.add(A);
		points.add(bigLongitude);
		GPXtrkseg bigLongitudeSegment = new GPXtrkseg(points);
		ArrayList<GPXtrkseg> segments = new ArrayList<GPXtrkseg>();
		segments.add(bigLongitudeSegment);
		GPXtrk segmentWithBigLongitude = new GPXtrk("big longitude", segments);
		assertEquals(0.0, GPXcalculator.calculateDistanceTraveled(segmentWithBigLongitude), 0.0);
	}
	@Test
	// tests that distance calculated for a segment is 0 when a track has a longitude
	// less than -180
	public void testTooSmallLongitude() {
		GPXtrkpt A = new GPXtrkpt(70.0, 110.0, fakeDate);
		GPXtrkpt smallLongitude = new GPXtrkpt(80.0, -181.0, fakeDate);
		ArrayList<GPXtrkpt> points = new ArrayList<GPXtrkpt>();
		points.add(A);
		points.add(smallLongitude);
		GPXtrkseg smallLongitudeSegment = new GPXtrkseg(points);
		ArrayList<GPXtrkseg> segments = new ArrayList<GPXtrkseg>();
		segments.add(smallLongitudeSegment);
		GPXtrk segmentWithSmallLongitude = new GPXtrk("small longitude", segments);
		assertEquals(0.0, GPXcalculator.calculateDistanceTraveled(segmentWithSmallLongitude), 0.0);
	}
	@Test
	// tests that distance is correctly calculated when there is only one segment
	public void testCorrectSegmentDistance() {
		GPXtrkpt A = new GPXtrkpt(6.0, -8.0, fakeDate);
		GPXtrkpt B = new GPXtrkpt(0.0, 0.0, fakeDate);
		GPXtrkpt C = new GPXtrkpt(-6.0, 8.0, fakeDate);
		ArrayList<GPXtrkpt> points = new ArrayList<GPXtrkpt>();
		points.add(A);
		points.add(B);
		points.add(C);
		GPXtrkseg regularSegment = new GPXtrkseg(points);
		ArrayList<GPXtrkseg> segments = new ArrayList<GPXtrkseg>();
		segments.add(regularSegment);
		GPXtrk regularTrack = new GPXtrk("regular track", segments);
		assertEquals(20.0, GPXcalculator.calculateDistanceTraveled(regularTrack), 0.0);
		
	}
	@Test
	// tests that distance is correctly summed across various segments
	public void testCorrectSegmentSummation() {
		GPXtrkpt A = new GPXtrkpt(6.0, -8.0, fakeDate);
		GPXtrkpt B = new GPXtrkpt(0.0, 0.0, fakeDate);
		GPXtrkpt C = new GPXtrkpt(-6.0, 8.0, fakeDate);
		ArrayList<GPXtrkpt> points1 = new ArrayList<GPXtrkpt>();
		GPXtrkseg segment1 = new GPXtrkseg(points1);
		points1.add(A);
		points1.add(B);
		points1.add(C);
		GPXtrkpt D = new GPXtrkpt(78.0, -38.0, fakeDate);
		GPXtrkpt E = new GPXtrkpt(72.0, -30.0, fakeDate);
		ArrayList<GPXtrkpt> points2 = new ArrayList<GPXtrkpt>();
		points2.add(D);
		points2.add(E);
		GPXtrkseg segment2 = new GPXtrkseg(points2);
		ArrayList<GPXtrkseg> segments = new ArrayList<GPXtrkseg>();
		segments.add(segment1);
		segments.add(segment2);
		GPXtrk multipleSegments = new GPXtrk("summed up track", segments);
		assertEquals(30.0, GPXcalculator.calculateDistanceTraveled(multipleSegments), 0.0);
	}
}
