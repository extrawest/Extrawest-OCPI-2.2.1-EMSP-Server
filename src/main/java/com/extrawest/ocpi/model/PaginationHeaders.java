package com.extrawest.ocpi.model;


import java.util.Map;

public class PaginationHeaders {
    /* Link to the 'next' page should be provided when this is NOT the last page. The Link
    should also contain any filters present in the original request. See the examples
    below. e.g:  Link: <https://www.server.com/ocpi/cpo/2.2/cdrs/?offset=150&limit=50>; rel="next"
     */
    public final static String LINK = "Link";

    /*
    The total number of objects available in the server system that match the given query (including the given query
    parameters, for example: date_to and date_from but excluding limit and offset) and that are available to this
    client.
    */
    public final static String X_TOTAL_COUNT = "X-Total-Count";

   /* The maximum number of objects that the server can return.
    Note that this is an upper limit. If there are not enough remaining objects to return,
    fewer objects than this upper limit number will be returned, X-Limit SHALL then still
    show the upper limit, not the number of objects returned.
    */
   public final static String X_LIMIT = "X-Limit";
}