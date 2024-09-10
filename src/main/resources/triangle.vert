#version 330 core

layout (location = 0) in vec3 aPosition;
layout (location = 1) in vec4 aColor;
layout (location = 2) in vec2 aTexCoord;

out vec4 fColor;
out vec2 fTexCoord;

uniform mat4 modelViewMatrix;
uniform mat4 projectionMatrix;

void main() {
    gl_Position = projectionMatrix * modelViewMatrix * vec4(aPosition, 1.0);
    fColor = aColor;
    fTexCoord = aTexCoord;
}