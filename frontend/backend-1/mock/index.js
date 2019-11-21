import Mock from 'mockjs'

import user from './user'
import table from './table'

const mocks = [
  ...user,
  ...table
]

//使用mockjs模拟数据
export function mock() {
  debugger
  Mock.mock(mocks)
}