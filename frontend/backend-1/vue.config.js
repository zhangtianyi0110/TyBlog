const webpack = require('webpack')
const path = require('path')

/**
 * 拼接根路径和指定文件路径
 * @param {父目录为根路径的指定路径} dir
 */
function resolve(dir) {
  return path.join(__dirname, dir)
}

const name = 'Ty Blog'
//根据环境判断端口号
const port = process.env.port || 4000 // dev port

module.exports = {
  publicPath: '/',
  outputDir: 'dist',
  assetsDir: 'public',
  lintOnSave: process.env.NODE_ENV === 'development',//为true时候lint错误会输出编译警告，为false会直接编译失败，生产环境必须为false
  productionSourceMap: false,//如果你不需要生产环境的 source map，可以将其设置为 false 以加速生产环境构建
  devServer: {
    port: port,
    https: false,
    proxy: {
      [process.env.VUE_APP_BASE_API]: {
        target: `http://127.0.0.1:8080`,
        changeOrigin: true,
        secure: false,
        pathRewrite: {
          '^/dev-api': '/api'
        }
      }
    }
  },
  configureWebpack: {//webpack配置
    name: name,
    resolve: {
      alias: {
        //给src目录设置别名@
        '@': resolve('src')
      }
    },
    externals: {
      'font-awesome': 'font-awesome' // 配置使用icon的CDN
    },
    plugins:[
      new webpack.ProvidePlugin({
        $: 'jquery' ,
        'jQuery': 'jquery'
      })
    ]
  },
  chainWebpack(config) {
    // set preserveWhitespace
    config.module
    .rule('vue')
    .use('vue-loader')
    .loader('vue-loader')
    .tap(options => {
      //默认为 true。这意味着编译好的渲染函数会保留所有 HTML 标签之间的空格。
      //如果设置为 false，则标签之间的空格会被忽略。这能够略微提升一点性能但是可能会影响到内联元素的布局。
      options.compilerOptions.preserveWhitespace = true
      return options
    })
    .end()

    config
    // https://webpack.js.org/configuration/devtool/#development
      .when(process.env.NODE_ENV === 'development',
        config => config.devtool('cheap-source-map')
      )

    config
      .when(process.env.NODE_ENV !== 'development',
        config => {
          config
            .plugin('ScriptExtHtmlWebpackPlugin')
            .after('html')
            .use('script-ext-html-webpack-plugin', [{
            // `runtime` must same as runtimeChunk name. default is `runtime`
              inline: /runtime\..*\.js$/
            }])
            .end()
          config
            .optimization.splitChunks({
              chunks: 'all',
              cacheGroups: {
                libs: {
                  name: 'chunk-libs',
                  test: /[\\/]node_modules[\\/]/,
                  priority: 10,
                  chunks: 'initial' // only package third parties that are initially dependent
                },
                elementUI: {
                  name: 'chunk-elementUI', // split elementUI into a single package
                  priority: 20, // the weight needs to be larger than libs and app or it will be packaged into libs or app
                  test: /[\\/]node_modules[\\/]_?element-ui(.*)/ // in order to adapt to cnpm
                },
                commons: {
                  name: 'chunk-commons',
                  test: resolve('src/components'), // can customize your rules
                  minChunks: 3, //  minimum common number
                  priority: 5,
                  reuseExistingChunk: true
                }
              }
            })
          config.optimization.runtimeChunk('single')
        }
      )
  }
}