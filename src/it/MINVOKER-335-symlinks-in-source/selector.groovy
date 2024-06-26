/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.nio.file.Files
import java.nio.file.Paths

def projectPath = new File(basedir, 'src/it/clone-symlinks').toPath()

def testDir = projectPath.resolve('testDir');
def testFile = projectPath.resolve('test.txt')

Files.createDirectory(testDir)
Files.createFile(testFile)

// If FS does not support symlinks we should skip test
try {
    Files.createSymbolicLink(projectPath.resolve('testDirLink'), Paths.get('testDir'))
    Files.createSymbolicLink(projectPath.resolve('testLink.txt'), Paths.get('test.txt'))
} catch (IOException e) {
    return false
}

return true
