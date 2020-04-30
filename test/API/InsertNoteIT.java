///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package API;
//
//import java.sql.Connection;
//import java.sql.Statement;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.sql.DataSource;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import static org.mockito.Mockito.*;
//import org.mockito.MockitoAnnotations;
//
///**
// *
// * @author mmunteanu
// */
//public class InsertNoteIT {
//
//    @Mock
//    final Statement statement;
//    @Mock
//    final Connection connection;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    /**
//     * Test of randomString method, of class InsertNote.
//     */
//    @Test
//    public void testRandomString() {
//    }
//
//    /**
//     * Test of processRequest method, of class InsertNote.
//     */
//    @Test
//    public void testProcessRequest() throws Exception {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        when(request.getParameter("textarea_content")).thenReturn("me");
//        when(request.getParameter("epwd")).thenReturn("");
//        when(request.getParameter("apwd")).thenReturn("private");
//
//        //when(dbRes.getConnection()).thenReturn(mockConnection);
//        new InsertNote().doPost(request, response);
//
//    }
//
//    /**
//     * Test of doGet method, of class InsertNote.
//     */
//    @Test
//    public void testDoGet() throws Exception {
//    }
//
//    /**
//     * Test of doPost method, of class InsertNote.
//     */
//    @Test
//    public void testDoPost() throws Exception {
//    }
//
//    /**
//     * Test of getServletInfo method, of class InsertNote.
//     */
//    @Test
//    public void testGetServletInfo() {
//    }
//
//}
