const { client, index, type } = require('./connection-product')


/*

    "id": id, 
    "modelname":modelname,
    "modelnumber": modelnumber,
    "brand": brand, 
    "productname" :productname, 
    "shortlabel":shortlabel, 
    "longdescription":longdescription
    */
/*
module.exports = {  
  queryTerm (term, offset = 0) {
    const body = {
      from: offset,
      query: { 
        multi_match: {
            query: term,
            fields: [ "id", "modelname", "modelnumber", "brand", "productname", "shortlabel", "longdescription" ]
                }      
        }
    }

    return client.search({ index, type, body })
  }
}
*/
module.exports = {
    /** Query ES index for the provided term */
    queryTerm (term, offset = 0) {
      const body = {
        from: offset,
        query: { match: {
          text: {
            query: term,
            operator: 'and',
            fuzziness: 'auto'
          } } },
        highlight: { fields: { text: {} } }
      }
  
      return client.search({ index, type, body })
    },

    queryRangeOnCurrentprice (min, max, order ='desc' ,offset = 0) {

      const body = {
        "sort" : [        
          { "currentprice" : order }
        ],
        from: offset,
        query: { range: {
          currentprice: {
            "gte" : min,
            "lte" : max,
            "boost" : 2.0
          } } },
        highlight: { fields: { text: {} } }
      }
      console.log("queryRangeOnCurrentprice", JSON.stringify(body));
      return client.search({ index, type, body })
    }
  }