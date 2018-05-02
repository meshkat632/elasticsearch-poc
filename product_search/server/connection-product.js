const elasticsearch = require('elasticsearch')

// Core ES variables for this project
const index = 'smart-tv'
const type = 'smart-tv'
const port = 9200
const host = process.env.ES_HOST || 'localhost'
const client = new elasticsearch.Client({ host: { host, port } })

/** Check the ES connection status */
async function checkConnection () {
  let isConnected = false
  while (!isConnected) {
    console.log('Connecting to ES')
    try {
      const health = await client.cluster.health({})
      console.log(health)
      isConnected = true
    } catch (err) {
      console.log('Connection Failed, Retrying...', err)
    }
  }
}


/** Clear the index, recreate it, and add mappings */
async function resetIndex () {
    if (await client.indices.exists({ index })) {
      await client.indices.delete({ index })
    }
  
    await client.indices.create({ index })
    await putMappingForSmartTV()
  }

  /** Add book section schema mapping to ES */
async function putMappingForSmartTV() {

  /*
  const ret = {
    "id": id, 
    "modelname":modelname,
    "modelnumber": modelnumber,
    "brand": brand, 
    "productname" :productname, 
    "shortlabel":shortlabel, 
    "longdescription":longdescription
  };
  */
  /*
    const schema = {
      id:  { type: 'text' },
      modelname: { type: 'text' },
      modelnumber: { type: 'text' },
      brand: { type: 'text' },
      productname: { type: 'text' },
      shortlabel: { type: 'text' },
      longdescription: { type: 'text' },
      currentprice: { type: 'text' }
      
    }
    */
    /*
    const schema = {
      brand: { type: 'text' }      
    }
    */

    const schema = {
      "id": { type: 'keyword' }      , 
      "modelname":{ type: 'keyword' }      ,
      "modelnumber": { type: 'keyword' }      ,
      "brand": { type: 'keyword' }      , 
      "productname" :{ type: 'keyword' }      , 
      "shortlabel":{ type: 'keyword' }      ,       
      "displayname":{ type: 'keyword' }      ,      
      "ean":{ type: 'keyword' } ,      
      "currentprice":{ type: 'float' }, 
      "text": { type: 'text' } 
    };
  
    return client.indices.putMapping({ index, type, body: { properties: schema } })
  }

  module.exports = {
    client, index, type, checkConnection, resetIndex
  }