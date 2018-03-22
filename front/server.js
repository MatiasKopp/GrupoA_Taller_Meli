let config      = require("./config.json");
let express     = require("express");
let app         = express();
let router      = express.Router();
let viewPath    = __dirname + "/public/views/";
let publicPath  = __dirname + "/public/";

router.use(function (req,res,next) {
  next();
});

router.get("/", (req,res) => {
  res.sendFile(viewPath + "index.html");
});

app.use("/", router);
app.use("/stylesheets", express.static(publicPath + 'stylesheets'));;
app.use("/scripts", express.static(publicPath + 'scripts'));
app.use("/lib", express.static(publicPath + 'lib'));
app.use("/views", express.static(publicPath + 'views'));
app.use("/config.json", express.static(publicPath + 'config.json'));

app.use("*", function(req,res){
  res.sendFile(viewPath + "404.html");
});

app.listen(config.port, () => {
    console.log("Listening on http://%s:%s", config.host, config.port);
});
