#!/bin/sh
# Given two tags, creates a full OpenJDK patchset from the
# upstream source repository. 

# OpenJDK does not provide a source drop corresponding to every
# official OpenJDK release. This script can be used to generate
# patchsets between the last source drop and the current JDK
# version.

REPO=https://hg.openjdk.java.net/jdk7u/jdk7u
TO=jdk7u45-b18   # The target release tag.

SRC="$1"
DIR="patch-dir-${TO}"

if [ ! -d "${SRC}" ]; then
    echo "No source directory specified"
    exit 1
fi

if [ ! -d "${DIR}" ]; then
    hg clone http://hg.openjdk.java.net/jdk7u/jdk7u "${DIR}" 1>&2 || exit 1
fi

pushd "${DIR}" >/dev/null || exit 1

sh ./get_source.sh 1>&2 || exit 1
sh ./make/scripts/hgforest.sh "checkout 'tag(\"$TO\")'" 1>&2 || exit 1

diff -ruN -x ".hg" "$1" "."

popd >/dev/null
