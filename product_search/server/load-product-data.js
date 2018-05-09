const fs = require('fs')
const path = require('path')
const esConnection = require('./connection-product')

/** Clear ES index, parse and index all files from the books directory */
async function readAndInsertSmartTvs () {
  try {
    // Clear previous ES index
    await esConnection.resetIndex()

    // Read books directory
    let files = fs.readdirSync('./smart-tvs') ;//.filter(file => file.slice(-4) === '.json')
    console.log(`Found ${files.length} Files`)

    var count = 0;
    var bulkOps = [];
    // Read each book file, and index each paragraph in elasticsearch
    for (let file of files) {
      count ++
      console.log(`Reading File - ${file}`)
      const filePath = path.join('./smart-tvs', file)      
      var smartTv = parseSmartTVFile(filePath)
      bulkOps.push({ index: { _index: esConnection.index, _type: esConnection.type } })
      bulkOps.push(smartTv);
      //await esConnection.client.bulk({ body: smartTv })      
      /*
      const { title, author, paragraphs } = parseSmartTVFile(filePath)
      await insertBookData(title, author, paragraphs)
      */
      //if(count == 10000) break
    }
    
    await esConnection.client.bulk({ body: bulkOps })      
  } catch (err) {
    console.error(err)
  }
}

/** Read an individual book text file, and extract the title, author, and paragraphs */
function parseSmartTVFile (filePath) {
    // Read json file
    var smarttv = JSON.parse(fs.readFileSync(filePath, 'utf8'));    
    let { categories, currentprice, id, modelname,modelnumber,brand, productname, shortlabel:shortlabel, longdescription:longdescription, manufacturerimage, displayname, customerrating, ean, seoUrl } = smarttv;

    const ret = {
      "id": id, 
      "modelname":modelname,
      "modelnumber": modelnumber,
      "brand": brand, 
      "productname" :productname, 
      "shortlabel":shortlabel,       
      "manufacturerimage": manufacturerimage,
      "displayname":displayname,
      "customerrating": parseFloat(customerrating),
      "ean":ean,
      "seoUrl": seoUrl,
      "categories": categories,
      "currentprice": parseFloat(currentprice),
      "text": JSON.stringify(smarttv)
    };
    console.log("smarttv:"+JSON.stringify(ret));
    return ret;
     
  }

 

readAndInsertSmartTvs()